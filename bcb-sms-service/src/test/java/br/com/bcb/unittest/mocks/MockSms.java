package br.com.bcb.unittest.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bcb.enums.MessageType;
import br.com.bcb.model.Sms;

public class MockSms {
    public Sms mockEntity() {
        return mockEntity(0);
    }
    
    public List<Sms> mockEntityList() {
        List<Sms> sms = new ArrayList<Sms>();
        for (int i = 0; i < 12; i++) {
            sms.add(mockEntity(i));
        }
        return sms;
    }
    
    public Page<Sms> mockEntityPage(Pageable pageable) {
    	List<Sms> sms = new ArrayList<Sms>();
        for (int i = 0; i < pageable.getPageSize(); i++) {
            sms.add(mockEntity(i));
        }
        Page<Sms> smsPage = new PageImpl<>(sms, pageable, pageable.getPageSize());
        return smsPage;
    }
  
    public Sms mockEntity(Integer number) {
    	Sms sms = new Sms();
    	sms.setClientId(number.longValue());
    	sms.setPhoneNumber("phone"+number);
    	sms.setMessageType(number%2 == 0 ? MessageType.SMS : MessageType.WHATSAPP);
    	sms.setTextSms("text"+number);
        return sms;
    }
}
