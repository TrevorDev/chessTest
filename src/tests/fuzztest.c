#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <pthread.h>

#define RAND_CHAR (rand() % ('Z' - 'A') + 'A')
#define RAND_DIGIT (rand() % ('9' - '0') + '0')

int popen2(const char *cmdline, int *to, int *from);
void *readProcess(void *arg);

volatile int killSwitch;

int main(int argc, char **argv)
{
    pid_t p;
    FILE *fp, *fp2;
    int readFd, writeFd;
    char *randomData;
    int savedFdFlags;
    int i;
    int size = 1000000;

    chdir("../../bin/");
    system("cp boards/*.class .");
    system("cp rules/*.class .");
    system("cp chessTest/*.class .");

    
    fp = popen("java chessTest/Game 2>&1", "w");
    fprintf(fp, "1\n");
    fflush(fp);

    srand(50); // play with this for reproducablity 

    // feed in giberish adhering to our format
    for (i = 0; i < 100; i++) {
        fprintf(fp, "%c%c,%c%c\n", RAND_CHAR, RAND_DIGIT, RAND_CHAR,
          RAND_DIGIT);
        fflush(fp);
    }

    fprintf(fp, "q\n");
    fflush(fp);
    fclose(fp);

    sleep(1);


    fp2 = popen("java chessTest/Game 2>&1", "w");
    fprintf(fp2, "3\n");
    fflush(fp2);

    // feed in total gibberish
    for (i = 0; i < 100; i++) {
        //printf("feeding in gibberish...\n");
        fprintf(fp2, "%c%c%c%c%c\n", RAND_CHAR, RAND_CHAR, RAND_CHAR, RAND_CHAR,
          RAND_CHAR);
        fflush(fp2);
    }
    fclose(fp2);

#ifdef EDEBUG
    printf("let's get a little more complex...\n");
    killSwitch = 0;
    savedFdFlags = fcntl(readFd, F_GETFL);
    fcntl(readFd, F_SETFL, savedFdFlags & ~O_NONBLOCK);
    pthread_create(NULL, NULL, readProcess, (void *) &readFd);
#endif

    return 0;
}

void *readProcess(void *arg)
{
    int *fd = (int *) arg;
    char buf[1000];
    while (!killSwitch) {
        printf("%s", buf);
    }
    return NULL;
}

pid_t popen2(const char *command, int *infp, int *outfp)
{
    int p_stdin[2], p_stdout[2];
    pid_t pid;

    if (pipe(p_stdin) != 0 || pipe(p_stdout) != 0) {
        return -1;
    }
    pid = fork();

    if (pid < 0) {
        return pid;
    }
    else if (pid == 0) {
        close(p_stdin[0]);
        dup2(p_stdin[1], 1);
        close(p_stdout[1]);
        dup2(p_stdout[0], 0);

        execl("/bin/sh", "sh", "-c", command, NULL);
        perror("execl");
        exit(1);
    }


    if (infp == NULL) {
        close(p_stdin[1]);
    }
    else {
        *infp = p_stdin[1];
    }

    if (outfp == NULL) {
        close(p_stdout[0]);
    }
    else {
        *outfp = p_stdout[0];
    }

    return pid;
}

