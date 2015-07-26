//
//  ODFixScreen.h
//  ADMall
//
//  Created by Amy on 15/6/8.
//  Copyright (c) 2015年 AoDing. All rights reserved.
//

#ifndef ADMall_ODFixScreen_h
#define ADMall_ODFixScreen_h

#define kScreenWidth ([[UIScreen mainScreen] bounds].size.width)
#define kScreenHeight ([[UIScreen mainScreen] bounds].size.height)

CG_INLINE CGFloat ADFixHeightFlaot(CGFloat height) {
    if (kScreenHeight/548 <1) {
        return height;
    }
    height = height*kScreenHeight/548;
    return height;
}

CG_INLINE CGFloat ADReHeightFlaot(CGFloat height) {
    if (kScreenHeight/548< 1) {
        return height;
    }
    height = height*548/(kScreenHeight);
    return height;
}

CG_INLINE CGFloat ADFixWidthFlaot(CGFloat width) {
    if (kScreenWidth/320 < 1) {
        return width;
    }
    width = width*kScreenWidth/320;
    return width;
}

CG_INLINE CGFloat ADReWidthFlaot(CGFloat width) {
    if (kScreenWidth/320 < 1) {
        return width;
    }
    width = width*320/kScreenWidth;
    return width;
}

CG_INLINE CGRect ADRectMake(CGFloat x, CGFloat y, CGFloat width, CGFloat height)
{
    CGRect rect;
    rect.origin.x = ADFixWidthFlaot(x); rect.origin.y = ADFixHeightFlaot(y);
    rect.size.width = ADFixWidthFlaot(width); rect.size.height = ADFixHeightFlaot(height);
    return rect;
}

CG_INLINE CGFloat ODFixWidth(CGFloat width) {
    return floor(kScreenWidth * width / 320.0);
}

#endif
