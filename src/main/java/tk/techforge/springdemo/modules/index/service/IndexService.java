package tk.techforge.springdemo.modules.index.service;

import tk.techforge.springdemo.modules.user.bean.User;

public interface IndexService {


    public User getIndex(String key);

    public User updateIndex(String key, String m);

}
