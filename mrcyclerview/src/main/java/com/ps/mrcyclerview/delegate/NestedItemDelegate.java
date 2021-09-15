package com.ps.mrcyclerview.delegate;

import com.ps.mrcyclerview.SubData;

import java.util.List;

/**
 * Created by PengSong on 18/11/21.
 */

public interface NestedItemDelegate extends ItemDelegate {

    List<SubData> getSubItemDatas();

}
