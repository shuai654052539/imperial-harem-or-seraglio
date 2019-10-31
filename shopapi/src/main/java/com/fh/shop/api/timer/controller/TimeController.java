package com.fh.shop.api.timer.controller;

import com.fh.shop.api.timer.biz.TimeService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 */
@RestController
public class TimeController {
  @Resource(name = "timeService")
  private TimeService timeService;

  public void timedTask(){
    timeService.timedTask();
  }
}
