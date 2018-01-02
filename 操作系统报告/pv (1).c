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
        fprintf(stderr, "ɾ�� semaphoreʧ��\n");
    }
}
int P(int sem_id){
    struct sembuf sem_b;

    sem_b.sem_num=0;
    sem_b.sem_op=-1;
    sem_b.sem_flg=SEM_UNDO;
    if(semop(sem_id,&sem_b,1)==-1){
        fprintf(stderr, "P ����ʧ��\n");
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
        fprintf(stderr, "V ����ʧ��\n");
        return(1);
    }
    return(0);
}
int main(int argc, char const *argv[]) {
    int s1,s2;
    s1=semget(IPC_PRIVATE,1,IPC_CREAT);//douke
    s2=semget((key_t)34,1,IPC_CREAT);
    if (set_semvalue(s1,0)==-1||set_semvalue(s2,0)==-1) {
        fprintf(stderr, "��ʼ�� semvalueʧ��\n");
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
	    printf("˾���������ﳵվͣ��\n");
            V(s2);
            P(s1);
 	    printf("���������뿪��վ\n");
        }
    }
    else{
            while (1) {
                sleep(2);
	        printf("��ƱԱ��Ʊ\n");
                P(s2);
		printf("�򿪳��ţ��˿��ϳ������ϳ���\n");
                V(s1);
  		
            }
        }
exit(0);
    }
