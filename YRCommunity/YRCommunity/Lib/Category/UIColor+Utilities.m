//
//  UIColor+Utilities.m
//  
//
//  Created by Amy on 15/4/9.
//  Copyright (c) 2015年 Aoding. All rights reserved.
//

#import "UIColor+Utilities.h"

@implementation UIColor (Utilities)

+ (UIColor *)randomColor
{
    CGFloat red = (CGFloat)arc4random_uniform(255)/255.0f;
    CGFloat green = (CGFloat)arc4random_uniform(255)/255.0f;
    CGFloat blue = (CGFloat)arc4random_uniform(255)/255.0f;
    return [UIColor colorWithRed:red green:green blue:blue alpha:1.0];
}



@end
