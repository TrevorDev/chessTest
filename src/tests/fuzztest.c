#include <sys/types.h>
#include <unistd.h>

int main(int argc, char **argv)
{
   return 0; 
}

int popen2(const char *cmdline, pid_t *child_pid, int *to, int *from) {
    pid_t p;
    int pipe_stdin[2], pipe_stdout[2];

    if (pipe(pipe_stdin)) {
        return -1;
    } 
    if (pipe(pipe_stdout)) {
        return -1;
    } 

    printf("pipe_stdin[0] = %d, pipe_stdin[1] = %d\n", pipe_stdin[0], pipe_stdin[1]);
    printf("pipe_stdout[0] = %d, pipe_stdout[1] = %d\n", pipe_stdout[0], pipe_stdout[1]);

    p = fork();
    if (p < 0) {
        return p; /* Fork failed */
    } 
    if(p == 0) { /* child */
        close(pipe_stdin[1]);
        dup2(pipe_stdin[0], 0);
        close(pipe_stdout[0]);
        dup2(pipe_stdout[1], 1);
        execl("/bin/sh", "sh", "-c", cmdline, 0);
        perror("execl");
        exit(99);
    }
    *child_pid = p;
    *to = pipe_stdin[1];
    *from = pipe_stdout[0];
    return 0; 
}

