package aqcxbom.myxposed;
import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
/**
 * Created by AqCxBoM on 2016/12/24.
 */
public class XposedMain implements IXposedHookLoadPackage
{
    public String TAG = "AqCxBoM" ;
    private final String mStrPackageName = "aqcxbom.xposedhooktarget";
    private final String mStrClassPath = "aqcxbom.xposedhooktarget.MyClass";
    private final String mStrMethodName = "fun1";
    private void LOGI(String ct){ Log.d(TAG, ct); }
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals(mStrPackageName)) {
            LOGI("found target: " + loadPackageParam.packageName);
            final Class<?> ArgClass= XposedHelpers.findClass("aqcxbom.xposedhooktarget.ArgClass", loadPackageParam.classLoader);
            final Class<?> ArrayList= XposedHelpers.findClass("java.util.ArrayList", loadPackageParam.classLoader);
            final Class<?> Map= XposedHelpers.findClass("java.util.Map", loadPackageParam.classLoader);
            XposedHelpers.findAndHookMethod(mStrClassPath, loadPackageParam.classLoader, mStrMethodName,
                    "[[Ljava.lang.String;",
                    Map,
                    Map,
                    Map,
                    ArrayList,
                    ArrayList,
                    ArgClass,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param); //这个函数会在被hook的函数执行前执行
                            LOGI("beforeHook");
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);//这个函数会在被hook的函数执行后执行
                            LOGI("afterHooke param: ");
                        }
                    });
        }
    }
}