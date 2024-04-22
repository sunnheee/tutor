package com.sh.sys.service;

import com.sh.sys.model.TutorSub;

import java.util.List;

public interface IndexService {

    List<TutorSub> getTutorByIndexConfigType(byte type);

}
