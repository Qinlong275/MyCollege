#include<string.h>
#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/wait.h>

int main(void)
{
int status;
int chan1[2],chan2[2];
pid_t pid;
pid_t myPid,myParentPid;
gid_t myGid;
uid_t myUid;
myPid=getpid();
myParentPid=getppid();
myGid=getgid();
myUid=getuid();
char parent[50];
char child[50];
char buff[50];
if(pipe(chan1)<0)//创建管道要在创建进程之前 
printf("pipe1 creat error");
if(pipe(chan2)<0)
printf("pipe2 creat error");
if((pid=fork())==-1)
{
printf("fork error");
exit(1);
}
else if(pid==0)
{
printf(">>Child process starting...\n");
printf("childpid=%d\n",myPid);
printf("parentpid=%d\n",myParentPid);
printf("gid=%d\n",myGid);
printf("Uid=%d\n",myUid);
sleep(5);
close(chan1[1]);
close(chan2[0]);
if(read(chan1[0],buff,50)<=0)
printf("recieve error");
else
printf("child progress : %s\n",buff);
printf("Input message you want to send to parent process:\n");
fgets(child,100,stdin);
if(write(chan2[1],child,sizeof(child))!=sizeof(child))
printf("send error");
close(chan2[1]);
}

else
{
printf(">>Father process starting...\n");
sleep(10);
close(chan1[0]);
close(chan2[1]);
printf("Input message you want to send to child process:\n");

fgets(parent,100,stdin);
if(write(chan1[1],parent,sizeof(parent))!=sizeof(parent))
printf("send error");
close(chan1[1]);
if(read(chan2[0],buff,50)<=0)
printf("recieve error");
else
printf("parent progress : %s\n",buff);
close(chan2[0]);
}
}
