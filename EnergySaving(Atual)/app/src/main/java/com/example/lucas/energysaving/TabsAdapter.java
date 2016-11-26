package com.example.lucas.energysaving;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int idx) {
        if (idx == 0) {
            return new FragmentHistoricoConsumo();
        } else
            return new FragmentHistoricoDicas();
    }
}
