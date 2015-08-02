//
//  RegisterViewController.m
//  YRCommunity
//
//  Created by 良好心态 on 15/8/2.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "RegisterViewController.h"
#import "UIView+CustomView.h"
#import "YRUserObject.h"
#import "MBProgressHUD+CMBProgressHUD.h"

static const CGFloat kLeftSpace = 15.0;
static const CGFloat kTopSpace = 10;
static const CGFloat kBackViewHeight = 150.0;
static const CGFloat kTextFieldLeft = 12.0;
static const CGFloat kTextFieldTop = 20.0;


@interface RegisterViewController ()<UITextFieldDelegate>

@end

@implementation RegisterViewController{

    
    UITextField *_userTextField;
    UITextField *_passwordTextField;
    UITextField *_confirmPWTextField;
    

}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = NSLocalizedString(@"欢迎注册", nil);
    [self createContent];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - createView

- (void)createContent
{
    self.view.backgroundColor = [UIColor lightGrayColor];
    UIView *_backView = [[UIView alloc] initWithFrame:CGRectMake( kLeftSpace, kTopSpace,self.view.width - kLeftSpace * 2 ,kBackViewHeight)];
    _backView.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:_backView];
    
    _userTextField = [[UITextField alloc] initWithFrame:CGRectMake( kTextFieldLeft, kTextFieldTop, _backView.width - kTextFieldLeft, 25)];
    _userTextField.delegate = self;
    _userTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    _userTextField.font = [UIFont systemFontOfSize:12];
    _userTextField.placeholder = NSLocalizedString(@"用户名:限字母、数字和汉字", nil);
    _userTextField.backgroundColor = [UIColor clearColor];
    [_backView addSubview:_userTextField];
    
    UIImageView *_lineImage = [[UIImageView alloc] initWithFrame:CGRectMake( kTextFieldLeft, _userTextField.bottom,_backView.width  - kTextFieldLeft * 2 , 1)];
    _lineImage.backgroundColor = [UIColor lightGrayColor];
    [_backView addSubview:_lineImage];
    
    _passwordTextField = [[UITextField alloc] initWithFrame:CGRectMake( kTextFieldLeft, _userTextField.bottom + kTextFieldTop, _backView.width - kTextFieldLeft, 25)];
    _passwordTextField.delegate = self;
    _passwordTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    _passwordTextField.font = [UIFont systemFontOfSize:12];
    
    _passwordTextField.secureTextEntry = YES;
    _passwordTextField.placeholder = NSLocalizedString(@"6-18位字母与数字组合", nil);
    [_backView addSubview:_passwordTextField];
    
    _confirmPWTextField = [[UITextField alloc] initWithFrame:CGRectMake( kTextFieldLeft, _passwordTextField.bottom + kTextFieldTop, _backView.width - kTextFieldLeft, 25)];
    _confirmPWTextField.delegate = self;
    _confirmPWTextField.secureTextEntry = YES;
    _confirmPWTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    _confirmPWTextField.font = [UIFont systemFontOfSize:12];
    _confirmPWTextField.placeholder = NSLocalizedString(@"确认密码", nil);

    [_backView addSubview:_confirmPWTextField];

    
    UIImageView *_lineImage1 = [[UIImageView alloc] initWithFrame:CGRectMake( kTextFieldLeft, _passwordTextField.bottom,_backView.width  - kTextFieldLeft * 2 , 0.5)];
    _lineImage1.backgroundColor = [UIColor lightGrayColor];
    [_backView addSubview:_lineImage1];
    
    UIImageView *_lineImage2 = [[UIImageView alloc] initWithFrame:CGRectMake( kTextFieldLeft, _confirmPWTextField.bottom,_backView.width  - kTextFieldLeft * 2 , 0.5)];
    _lineImage2.backgroundColor = [UIColor lightGrayColor];
    [_backView addSubview:_lineImage2];

    
    UIButton *registerButton = [UIButton buttonWithType:UIButtonTypeCustom];
    registerButton.frame = CGRectMake(kTextFieldLeft, _backView.bottom + 20, self.view.width - kTextFieldLeft * 2, 35);
    registerButton.backgroundColor = [UIColor greenColor];
    [registerButton setTitle:NSLocalizedString(@"登陆", nil) forState:UIControlStateNormal];
    registerButton.titleLabel.font = [UIFont systemFontOfSize:12];
    [registerButton addTarget:self action:@selector(buttonRegisterClick) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:registerButton];
    
    
}

#pragma mark - action

- (void)buttonRegisterClick
{
    if ([_confirmPWTextField.text isEqualToString:_passwordTextField.text]) {
        [[YRUserObject loginUser]registerWithUserName:_userTextField.text andPassWord:_passwordTextField.text];
    }else
    {
        [MBProgressHUD showHUDOnlyTextAddedTo:self.view labelText:NSLocalizedString(@"密码不一致,请您重新输入密码", nil) afterDelay:0.5];
    }
}



/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
