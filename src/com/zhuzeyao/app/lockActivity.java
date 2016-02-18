package com.zhuzeyao.app;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class lockActivity extends Activity{
	private DevicePolicyManager policyManager;
	private ComponentName componentName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this, deviceAdminReceiver.class);
		boolean active = policyManager.isAdminActive(componentName);
        if (active) {
        	policyManager.lockNow();
        	finish();
        }else{
        	// 启动设备管理(隐式Intent) - 在AndroidManifest.xml中设定相应过滤器
    		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
    		
    		//权限列表
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            
            //描述(additional explanation)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "------ 其他描述 ------");
            
            startActivityForResult(intent, 0);
        } 
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		
	}
}
