//
//  YRUserObject.m
//  YRCommunity
//
//  Created by 良好心态 on 15/8/2.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "YRUserObject.h"
#import "AFHTTPRequestOperationManager.h"
#import "AFManager.h"

NSString * const kADLoginUserDefaultKey = @"kADLoginUserDefaultKey";

NSString * const ADUserLoginNotification = @"ADUserLoginNotification";
NSString * const ADUserLogoutNotification = @"ADUserLogoutNotification";
NSString * const ADUserUpdateInfoNotification = @"ADUserUpdateInfoNotification";

NSString * const ODUserOperationAESKey = @"3c2c9cdf20a4c0fa1d9a179b18fa6705";

static YRUserObject *kLoginUserObjectInstance = nil;


@implementation YRUserObject

+ (instancetype)loginUser
{
    if (kLoginUserObjectInstance == nil) {
        kLoginUserObjectInstance = [[YRUserObject alloc] init];
        NSDictionary *loginUserInfo = [[NSUserDefaults standardUserDefaults] objectForKey:kADLoginUserDefaultKey];
        //[kLoginUserObjectInstance setValuesForDictionary:loginUserInfo];
        // 初始化业务数据库
       // [ADBizModel setupBizDBWithUserID:[ADUserObject loginUser].userID];
    }
    return kLoginUserObjectInstance;
}


- (void)registerWithUserName:(NSString *)userName andPassWord:(NSString *)password
{
    
    AFManager *manager = [AFManager manager];
    NSDictionary *dict = @{@"username":userName,
                           @"password":password
                           };
    manager.responseSerializer = [AFJSONResponseSerializer serializer];
    NSLog(@"=========%@",[AFManager getUrlStrWithPath:KRegisterInterface]);
    [manager POST:[AFManager getUrlStrWithPath:KRegisterInterface] parameters:dict success:^(AFHTTPRequestOperation *operation, id responseObject) {
        MyLog(@"%@",operation.response);
        MyLog(@"responseObject %@",responseObject);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        MyLog(@"error %@",error);
    }];

}



@end
