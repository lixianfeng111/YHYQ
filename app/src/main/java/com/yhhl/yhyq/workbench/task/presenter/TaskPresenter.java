package com.yhhl.yhyq.workbench.task.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseModel;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.workbench.task.bean.TaskBean;
import com.yhhl.yhyq.workbench.task.view.TaskView;
import java.util.HashMap;
import java.util.List;

public class TaskPresenter extends BasePresenter<TaskView> {

    private final BaseModel baseModel;

    public TaskPresenter(TaskView iBaseView) {
        super(iBaseView);
        baseModel = new BaseModel();
    }

    public void task(String page){
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("per_page","20");
        baseModel.find_task(HttpUrlUtils.TASK, map, new BaseCallBack<List<TaskBean>>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().onDataFailed(e.getMessage());
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(List<TaskBean> taskBeans) {
                getiBaseView().onDataList(taskBeans);
            }
        });
    }
}