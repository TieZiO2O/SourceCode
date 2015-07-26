//
//  UIColor+Utilities.h
//
//
//  Created by Amy on 15/4/9.
//  Copyright (c) 2015年 Aoding. All rights reserved.
//

#import <UIKit/UIKit.h>

static inline UIColor *ColorForRGB(CGFloat r, CGFloat g, CGFloat b, CGFloat alpha)
{
    return [UIColor colorWithRed:r / 255.0 green:g / 255.0 blue:b / 255.0 alpha:alpha];
}

static inline UIColor *ColorForHexAlpha(int rgbValue, CGFloat alpha)
{
    return [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0
                           green:((float)((rgbValue & 0xFF00) >> 8))/255.0
                            blue:((float)(rgbValue & 0xFF))/255.0
                           alpha:alpha];
}

static inline UIColor *ColorForHex(int rgbValue)
{
    return ColorForHexAlpha(rgbValue, 1);
}

@interface UIColor (Utilities)

+ (UIColor *)randomColor;


@end
