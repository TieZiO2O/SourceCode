//
//  MBProgressHUD+CMBProgressHUD.h
//  WherePlay
//
//  Created by An Mingyang on 13-1-22.
//  Copyright (c) 2013年 An Mingyang. All rights reserved.
//


#import "MBProgressHUD.h"

static const CGFloat kMBPAfterDelay = 1.5;

@interface MBProgressHUD (CMBProgressHUD)

/*  对原有showHUDAddedTo方法的扩展,增加了labelText参数方便直接调用
    显示完后需要手动调动hideHUDForView 或 hideAllHUDsForView方法关闭隐藏
*/
+ (MBProgressHUD *)showHUDAddedTo:(UIView *)view
                        labelText:(NSString *)labelText;

/*  不带动画只显示纯文本,指定afterDelay的时间间隔后自动释放   */
+ (MBProgressHUD *)showHUDOnlyTextAddedTo:(UIView *)view
                                labelText:(NSString *)labelText
                               afterDelay:(NSTimeInterval)afterDelay;


+ (MBProgressHUD *)showODHUDAddedTo:(UIView *)view;

@end
