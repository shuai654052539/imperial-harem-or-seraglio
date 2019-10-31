package com.fh.shop.api.order.biz;

import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.order.po.Recipient;

public interface RecipientService {
  ServerResponse addRecipient(Recipient recipient, MemberVo memberVo);

  ServerResponse delRecipient(Long id);

  ServerResponse getRecipient(MemberVo memberVo);

  ServerResponse getRecipientById(Long id);

  ServerResponse updateRecipient(Recipient recipient);
}
