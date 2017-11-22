nohup java -jar osp-core.jar --spring.profiles.active=test >nohup.out &

echo Start Success!


tail -f nohup.out
