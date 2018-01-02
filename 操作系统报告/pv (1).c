#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/shm.h>
int set_semvalue(int sem_id,int value){
    return(semctl(sem_id,0,SETVAL,value));
}
void del_semvalue(int sem_id){

    if (semctl(sem_id,0,IPC_RMID,0)==-1) {
        fprintf(stderr, "删除 semaphore失败\n");
    }
}
int P(int sem_id){
    struct sembuf sem_b;

    sem_b.sem_num=0;
    sem_b.sem_op=-1;
    sem_b.sem_flg=SEM_UNDO;
    if(semop(sem_id,&sem_b,1)==-1){
        fprintf(stderr, "P 操作失败\n");
        return(1);
    }
    return(0);
}
int V(int sem_id){
    struct sembuf sem_b;

    sem_b.sem_num=0;
    sem_b.sem_op=1;
    sem_b.sem_flg=SEM_UNDO;//zidongshifangchiyouxinhaoliang
    if (semop(sem_id,&sem_b,1)==-1) {
        fprintf(stderr, "V 操作失败\n");
        return(1);
    }
    return(0);
}
int main(int argc, char const *argv[]) {
    int s1,s2;
    s1=semget(IPC_PRIVATE,1,IPC_CREAT);//douke
    s2=semget((key_t)34,1,IPC_CREAT);
    if (set_semvalue(s1,0)==-1||set_semvalue(s2,0)==-1) {
        fprintf(stderr, "初始化 semvalue失败\n");
        exit(EXIT_FAILURE);
    }
    pid_t p;
    if((p=fork())<0)
{	printf("creat error");
	exit(1);
}
    else if (p==0) {
        while(1){
            sleep(5);
	    printf("司机正常到达车站停车\n");
            V(s2);
            P(s1);
 	    printf("开动车辆离开车站\n");
        }
    }
    else{
            while (1) {
                sleep(2);
	        printf("售票员售票\n");
                P(s2);
		printf("打开车门，乘客上车，关上车门\n");
                V(s1);
  		
            }
        }
exit(0);
    }
