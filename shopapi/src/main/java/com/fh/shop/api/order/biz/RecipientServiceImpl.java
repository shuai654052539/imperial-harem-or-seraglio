package com.fh.shop.api.order.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.order.mapper.RecipientMapper;
import com.fh.shop.api.order.po.Recipient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("recipientService")
public class RecipientServiceImpl implements RecipientService {

  @Autowired
  private RecipientMapper recipientMapper;

  @Override
  public ServerResponse addRecipient(Recipient recipient, MemberVo memberVo) {
    Integer areaid1 = recipient.getAreaid1();
    Integer areaid2 = recipient.getAreaid2();
    Integer areaid3 = recipient.getAreaid3();
    String recipientName = recipient.getRecipientName();
    String recipientPhone = recipient.getRecipientPhone();
    String detailedAddress = recipient.getDetailedAddress();
    //非空判断 一大堆
    if (areaid1 == null || areaid2 == null || areaid3 == null || StringUtils.isEmpty(recipientName) || StringUtils.isEmpty(recipientPhone) || StringUtils.isEmpty(detailedAddress)){
        return ServerResponse.error();
    }
    recipient.setMemberId(memberVo.getId());
    recipientMapper.insert(recipient);
    return ServerResponse.success();
  }

  @Override
  public ServerResponse delRecipient(Long id) {
    recipientMapper.deleteById(id);
    return ServerResponse.success();
  }

  @Override
  public ServerResponse getRecipient(MemberVo memberVo) {
    List<Recipient> list = recipientMapper.getList(memberVo.getId());
    return ServerResponse.success(list);
  }

  @Override
  public ServerResponse getRecipientById(Long id) {
    Recipient recipient = recipientMapper.selectById(id);
    return ServerResponse.success(recipient);
  }

  @Override
  public ServerResponse updateRecipient(Recipient recipient) {
    recipientMapper.updateById(recipient);
    return ServerResponse.success();
  }
}
