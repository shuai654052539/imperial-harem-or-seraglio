package com.fh.shop.api.member.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.mapper.MemberDao;
import com.fh.shop.api.area.po.Member;
import com.fh.shop.api.goods.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memBerService")
public class MemBerServiceImpl implements MemBerService {

  @Autowired
  private MemberDao memberDao;

  @Override
  public ServerResponse get(String userName) {
    QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userName",userName);
    List<Member> members = memberDao.selectList(queryWrapper);
    if (members != null) {
      return ServerResponse.error();
    }
      return ServerResponse.success();
  }
}
