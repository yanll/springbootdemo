package tk.techforge.springdemo.modules.index.service;

import tk.techforge.springdemo.modules.index.bean.HomeVO;

public interface IndexService {

    public HomeVO getV(String key);

    public String getIndex(String key);

    public String updateIndex(String key);

}
