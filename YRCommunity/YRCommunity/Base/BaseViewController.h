//
//  BaseViewController.h
//  YRCommunity
//
//  Created by windear on 15-7-17.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RDVTabBarController.h"
#import "UIView+CustomView.h"
@interface BaseViewController : UIViewController<UITableViewDelegate,UITableViewDataSource>

//添加界面标题
//-(void)addVCTitleView:(UIView *)titleView;

-(void)addNormalTitle:(NSString *)title;
-(void)addImage:(NSString *)imgName andTitle:(NSString *)title;
/**
 添加左右按钮
 
 @param title 如果imgName不为空则title无效.
 @param imgName .
 @param selector .
 @param isLeft YES为左上角按钮 NO为右上角按钮.
 */
-(void)addItemWithTitle:(NSString *)title imageName:(NSString *)imgName selector:(SEL)selector location:(BOOL)isLeft;




/**
 添加左右按钮
 
 @param title .
 @param message .
 @param tag .
 @param isHave 是否有确定按钮.
 */
//普通提示框
-(void)showAlertWithTitle:(NSString *)title message:(NSString *)message andTag:(int)tag haveYesBtn:(BOOL)isHave;
//自动消失提示框
- (void)showMessage:(NSString *)message WithSecond:(int)second;




//滚轮指示器
//开始转圈
-(void)startIndicatorDelegateWithContent:(NSString *)content;
//结束转圈
-(void)stopIndicatorDelegate;




//下载
//获取网络状态
-(NSString *)getNetWorkState;
//网络是否连接
-(BOOL)isConnections;
/**
 网络请求
 
 @param url 此处url为PathUrl 可用作判断返回标识.
 @param dict 请求数据.
 @param tag 当同一url多次请求时可用tag判断.
 */
-(void)requestStartWithURL:(NSString *)url andData:(NSDictionary *)dict andTag:(NSInteger)tag;

//取消网络请求
-(void)cancelRequest;



//分享
-(void)shareWithModel:(id)shareModel;

@end
