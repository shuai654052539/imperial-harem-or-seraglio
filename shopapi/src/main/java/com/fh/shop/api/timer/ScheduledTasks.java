package com.fh.shop.api.timer;

import com.fh.shop.api.timer.biz.TimeService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

  @Resource(name = "timeService")
  private TimeService timeService;

  /*@Scheduled(fixedRate = 1000 * 30)
  public void reportCurrentTime(){
    System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date ()));
  }*/

  //每1分钟执行一次
 /* @Scheduled(cron = "0/5 * * * * ?")
  public void reportCurrentByCron(){
    System.out.println("11111");
    timeService.timedTask();
  }*/


}
