//
//  AFManager.m
//  YRCommunity
//
//  Created by windear on 15/7/22.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "AFManager.h"

@implementation AFManager

+(NSString *)getUrlStrWithPath:(NSString *)path
{
    return [YRan_URL stringByAppendingString:path];
}

@end
