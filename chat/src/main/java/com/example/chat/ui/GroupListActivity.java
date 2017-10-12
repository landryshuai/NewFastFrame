package com.example.chat.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chat.R;
import com.example.chat.adapter.GroupListAdapter;
import com.example.chat.bean.GroupTableMessage;
import com.example.chat.manager.MessageCacheManager;
import com.example.chat.manager.UserManager;
import com.example.commonlibrary.baseadapter.listener.OnSimpleItemClickListener;
import com.example.commonlibrary.cusotomview.ListViewDecoration;
import com.example.commonlibrary.cusotomview.ToolBarOption;


/**
 * 项目名称:    TestChat
 * 创建人:        陈锦军
 * 创建时间:    2017/1/10      10:03
 * QQ:             1981367757
 */
public class GroupListActivity extends SlideBaseActivity {
        private RecyclerView display;
        private GroupListAdapter groupListAdapter;



        @Override
        protected boolean isNeedHeadLayout() {
                return true;
        }

        @Override
        protected boolean isNeedEmptyLayout() {
                return true;
        }

        @Override
        protected int getContentLayout() {
                return R.layout.activity_group_list;
        }


        @Override
        public void initView() {
                display = (RecyclerView) findViewById(R.id.rcv_group_list_display);
        }

        @Override
        public void initData() {
                display.setLayoutManager(new LinearLayoutManager(this));
                display.setItemAnimator(new DefaultItemAnimator());
                display.setHasFixedSize(true);
                display.addItemDecoration(new ListViewDecoration(this));
                groupListAdapter = new GroupListAdapter();
                groupListAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
                        @Override
                        public void onItemClick(int position, View view) {
                                GroupTableMessage message = groupListAdapter.getData(position);
                                Intent intent = new Intent(GroupListActivity.this, ChatActivity.class);
                                intent.putExtra("groupId", message.getGroupId());
                                intent.putExtra("from", "group");
                                startActivity(intent);
                        }
                });
                display.setAdapter(groupListAdapter);
                initActionBar();
                groupListAdapter.addData(MessageCacheManager.getInstance().getAllGroupTableMessage());
        }

        private void initActionBar() {
                ToolBarOption toolBarOption = new ToolBarOption();
                toolBarOption.setAvatar(UserManager.getInstance().getCurrentUser().getAvatar());
                toolBarOption.setTitle("群列表");
                toolBarOption.setNeedNavigation(true);
                setToolBar(toolBarOption);
        }

        public static void start(Activity activity) {
                Intent intent = new Intent(activity, GroupListActivity.class);
                activity.startActivity(intent);
        }

        @Override
        public void updateData(Object o) {

        }
}
