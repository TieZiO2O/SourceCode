//
//  YRUserObject.h
//  YRCommunity
//
//  Created by 良好心态 on 15/8/2.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface YRUserObject : NSObject

+ (instancetype)loginUser;
- (void)registerWithUserName:(NSString *)userName andPassWord:(NSString *)password;


@end
