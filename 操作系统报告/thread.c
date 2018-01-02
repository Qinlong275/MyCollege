#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>

void *thread_function(void *arg);
int count=10;

int main(int argc, char const *argv[]) {
    int res;
    pthread_t a_thread;
    void *thread_result;
    printf("Process is running...\n");
    printf("Process count=%d\n",count);
    res=pthread_create(&a_thread,NULL,thread_function,(void*)&count);
    if (res!=0) {
        perror("Thread creation faild");
        exit(EXIT_FAILURE);
    }
    //printf("Waiting for thread to finish...\n");
    res=pthread_join(a_thread,&thread_result);
    if (res!=0) {
        perror("Thread join failed");
        exit(EXIT_FAILURE);
    }
    printf("status=%d\n",(*(int*)thread_result));
    printf("Thread finished\n");
    printf("Process finished\n");
}
void *thread_function(void *arg) {
    printf("New thread running\n");
    int sum=0;
    for(count=1;count<=5;count++){
        printf("Thread count=%d\n",count);
	sum+=count;
    }
    printf("sum=%d\n",sum);
    printf("thread sleep 5s\n");
    sleep(5);
    pthread_exit(arg);
}
