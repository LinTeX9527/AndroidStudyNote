package com.lintex9527.runoob;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * Android中的几种菜单
 * http://www.runoob.com/w3cnote/android-tutorial-menu.html
 *
 * 主要有3中菜单，如下：
 * 1、OptionsMenu：选项菜单，最常见的菜单，通过Menu键来调用
 * 2、SubMenu: 子菜单，点击子菜单项将弹出一个子菜单项的悬浮框，子菜单不支持嵌套
 * 3、ContextMenu: 上下文菜单，通过长按某个视图组件后出现的菜单，该组件需注册上下文菜单
 *
 * 【注意】子菜单和上下文菜单都无法显示图标，只有OptionsMenu才能显示图标。
 *
 * 关于OptionsMenu，一般只用重写下面两个方法即可
 * public boolean onCreateOptionsMenu(Menu menu) 初始化菜单项
 * public boolean onOptionsMenuSelected(MenuItem item) 菜单项被选中时触发，这里完成事件处理
 */
public class TestMenusActivity extends AppCompatActivity {

    // 定义不同颜色的菜单项的标志
    private final int RED = 110;
    private final int GREEN = 111;
    private final int BLUE = 112;
    private final int YELLOW = 113;
    private final int GRAY = 114;
    private final int CYAN = 115;
    private final int BLACK = 116;

    private TextView tv_test;
    private TextView tv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menus);

        tv_test = (TextView) findViewById(R.id.tv_test);

        tv_context = (TextView) findViewById(R.id.tv_context);
        // 必须调用方法 registerForContextMenu(View) 来显示地为某一个View注册上下文菜单
        registerForContextMenu(tv_context);
    }

    /**
     * 初始化选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 通过代码动态添加菜单项
        // 注意，选项菜单的排序从1开始
        menu.add(1, RED, 4, "红色");
        menu.add(1, GREEN, 2, "绿色");
        menu.add(1, BLUE, 3, "蓝色");
        menu.add(1, YELLOW, 1, "黄色");
        menu.add(1, GRAY, 5, "灰色");
        menu.add(1, CYAN, 6, "蓝绿色");
        menu.add(1, BLACK, 7, "黑色");
        return true;
    }


    /**
     * 选项菜单某个菜单项被选中时的事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case RED:
                tv_test.setTextColor(Color.RED);
                break;

            case GREEN:
                tv_test.setTextColor(Color.GREEN);
                break;

            case BLUE:
                tv_test.setTextColor(Color.BLUE);
                break;

            case YELLOW:
                tv_test.setTextColor(Color.YELLOW);
                break;

            case GRAY:
                tv_test.setTextColor(Color.GRAY);
                break;

            case CYAN:
                tv_test.setTextColor(Color.CYAN);
                break;

            case BLACK:
                tv_test.setTextColor(Color.BLACK);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 创建上下文菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 只用添加下面这两行代码就可以添加上下文菜单
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    /**
     * 上下文菜单被点击时的触发事件
     * @param item 被点击的菜单项
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.blue:
                tv_context.setTextColor(Color.BLUE);
                break;
            case R.id.green:
                tv_context.setTextColor(Color.GREEN);
                break;
            case R.id.red:
                tv_context.setTextColor(Color.RED);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
