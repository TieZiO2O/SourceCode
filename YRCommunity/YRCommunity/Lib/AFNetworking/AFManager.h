//
//  AFManager.h
//  YRCommunity
//
//  Created by windear on 15/7/22.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "AFHTTPRequestOperationManager.h"

@interface AFManager : AFHTTPRequestOperationManager

+(NSString *)getUrlStrWithPath:(NSString *)path;

@end
