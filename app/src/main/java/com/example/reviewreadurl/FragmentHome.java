package com.example.reviewreadurl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHome extends Fragment  {

    BottomNavigationView searchNavigation;
    //sinh ra full dung va sau do ta get no ve thi ????
    //dau tien : ???
    //no luu lai nhu la data cuc bo co the khong -> hoan toan phu hop dung ok
    //tiep theo : neu load thong tin co ban dang json cua 1 tram cuon sach ???
    //thi xay ra ntn ???
    //luu tru dang data tam thoi cua app -> duco ok
    //dung la nhu vay : chi khi nao ham doi voi 1 getView -> goi khi nao ???
    //khi view Da bi huy dung la nhu vay :
    //neu view van ton tai thi no ko di dau het ok
    //tiep tuc :
    //bang viec nhan thay set up cho PAger:
    //dau tien ta se co context dung la nhu vay : => ta dinh nghia 1 cai interface ->
    //de get ra duoc no ???
    //la cach thuc giao tiep 2 ben
    //tien hanh init () -> sinh ra 1 class tinh sau do thuc hien la duoc ???
    //co the khong ???
    //dau tien khi ma mainActivity khoi chay thi no se la nhu vay ???
    //dung ok
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        searchNavigation=view.findViewById(R.id.topNavigation);
        return  view;
    }



}
