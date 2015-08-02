//
//  LoginViewController.m
//  YRCommunity
//
//  Created by 良好心态 on 15/8/2.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "LoginViewController.h"
#import "RegisterViewController.h"
#import "UIView+CustomView.h"

static const CGFloat kLeftSpace = 15.0;
static const CGFloat kTopSpace = 10;
static const CGFloat kBackViewHeight = 120.0;
static const CGFloat kTextFieldLeft = 12.0;
static const CGFloat kTextFieldTop = 20.0;
static const CGFloat kResigerButtonWidth = 80.0;
@interface LoginViewController ()<UITextFieldDelegate>

@end

@implementation LoginViewController{

    UITextField *_userTextField;
    UITextField *_passwordTextField;
    

}

- (void)viewDidLoad {
    self.title = @"欢迎登录";
    [self createContent];
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
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
    _userTextField.placeholder = NSLocalizedString(@"请输入用户名", nil);
    _userTextField.backgroundColor = [UIColor clearColor];
    [_backView addSubview:_userTextField];
    
    UIImageView *_lineImage = [[UIImageView alloc] initWithFrame:CGRectMake( kTextFieldLeft, _userTextField.bottom,_backView.width  - kTextFieldLeft * 2 , 1)];
    _lineImage.backgroundColor = [UIColor lightGrayColor];
    [_backView addSubview:_lineImage];
    
    _passwordTextField = [[UITextField alloc] initWithFrame:CGRectMake( kTextFieldLeft, _userTextField.bottom + kTextFieldTop, _backView.width - kTextFieldLeft, 25)];
    _passwordTextField.delegate = self;
    _passwordTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    _passwordTextField.font = [UIFont systemFontOfSize:12];
    _passwordTextField.placeholder = NSLocalizedString(@"请输入密码", nil);
    [_backView addSubview:_passwordTextField];

    UIImageView *_lineImage1 = [[UIImageView alloc] initWithFrame:CGRectMake( kTextFieldLeft, _passwordTextField.bottom,_backView.width  - kTextFieldLeft * 2 , 1)];
    _lineImage1.backgroundColor = [UIColor lightGrayColor];
    [_backView addSubview:_lineImage1];
    
    UIButton *loginButton = [UIButton buttonWithType:UIButtonTypeCustom];
    loginButton.frame = CGRectMake(kTextFieldLeft, _backView.bottom + 20, self.view.width - kTextFieldLeft * 2, 35);
    loginButton.backgroundColor = [UIColor greenColor];
    [loginButton setTitle:NSLocalizedString(@"登陆", nil) forState:UIControlStateNormal];
    loginButton.titleLabel.font = [UIFont systemFontOfSize:12];
    [loginButton addTarget:self action:@selector(loginButtonClick) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:loginButton];
    
    UIButton *registerButton = [UIButton buttonWithType:UIButtonTypeCustom];
    registerButton.frame = CGRectMake( self.view.width - kLeftSpace - kResigerButtonWidth , loginButton.bottom,kResigerButtonWidth,30);
    [registerButton setTitle:NSLocalizedString(@"快速注册", nil) forState:UIControlStateNormal];
    registerButton.titleLabel.font = [UIFont systemFontOfSize:12];
    [registerButton setTitleColor:[UIColor greenColor] forState:UIControlStateNormal];
    [registerButton addTarget:self action:@selector(buttonRegisterClick) forControlEvents:
     UIControlEventTouchUpInside];
    registerButton.titleLabel.textAlignment = NSTextAlignmentRight;
    [self.view addSubview:registerButton];
    
    
}

#pragma mark - action

- (void)loginButtonClick
{

}

- (void)buttonRegisterClick
{
    RegisterViewController  *rvc = [[RegisterViewController  alloc] init];
    [self.navigationController pushViewController:rvc animated:YES];
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
