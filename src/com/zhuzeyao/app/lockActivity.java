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
        	// �����豸����(��ʽIntent) - ��AndroidManifest.xml���趨��Ӧ������
    		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
    		
    		//Ȩ���б�
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            
            //����(additional explanation)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "------ �������� ------");
            
            startActivityForResult(intent, 0);
        } 
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		
	}
}
