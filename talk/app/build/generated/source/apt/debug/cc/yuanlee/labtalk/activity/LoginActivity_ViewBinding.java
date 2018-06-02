// Generated code from Butter Knife. Do not modify!
package cc.yuanlee.labtalk.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cc.yuanlee.labtalk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131427495;

  private View view2131427496;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.usernameEditText = Utils.findRequiredViewAsType(source, R.id.username, "field 'usernameEditText'", EditText.class);
    target.passwordEditText = Utils.findRequiredViewAsType(source, R.id.password, "field 'passwordEditText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.login_forget_password, "method 'loginForgetPassword'");
    view2131427495 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.loginForgetPassword();
      }
    });
    view = Utils.findRequiredView(source, R.id.login_prompt, "method 'onLoginPrompt'");
    view2131427496 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginPrompt();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.usernameEditText = null;
    target.passwordEditText = null;

    view2131427495.setOnClickListener(null);
    view2131427495 = null;
    view2131427496.setOnClickListener(null);
    view2131427496 = null;
  }
}
