package com.example.baseadapter;

/**
 * Created by Xiamin on 2016/7/8.
 */
public class ItemBean {
    public int ItemImageResId;
    protected String Itemstring;
    public String ItemContent;

    public ItemBean(int itemImageResId,String itemstring, String itemContent) {
        this.Itemstring = itemstring;
        this.ItemImageResId = itemImageResId;
        this.ItemContent = itemContent;
    }


}
