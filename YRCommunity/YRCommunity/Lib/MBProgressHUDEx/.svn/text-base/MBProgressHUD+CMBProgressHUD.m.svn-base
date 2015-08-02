//
//  MBProgressHUD+CMBProgressHUD.m
//  WherePlay
//
//  Created by An Mingyang on 13-1-22.
//  Copyright (c) 2013年 An Mingyang. All rights reserved.
//

#import "MBProgressHUD+CMBProgressHUD.h"

@implementation MBProgressHUD (CMBProgressHUD)

+ (MBProgressHUD *)showHUDAddedTo:(UIView *)view
                        labelText:(NSString *)labelText
{
    MBProgressHUD *hud = [[MBProgressHUD alloc] initWithView:view];
    [view addSubview:hud];
    hud.labelText = labelText;
    [hud show:YES];
    return hud;
}

+ (MBProgressHUD *)showHUDOnlyTextAddedTo:(UIView *)view
                     labelText:(NSString *)labelText
                    afterDelay:(NSTimeInterval)afterDelay
{
    if (labelText != nil && ![labelText isEqualToString:@""]) {
        MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:view animated:YES];
        hud.mode = MBProgressHUDModeText;
        hud.detailsLabelFont = [UIFont systemFontOfSize:13.0];
        hud.detailsLabelText = labelText;
        hud.removeFromSuperViewOnHide = YES;
        [hud hide:YES afterDelay:afterDelay];
        return hud;
    }
    return nil;
}

+ (MBProgressHUD *)showODHUDAddedTo:(UIView *)view
{
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:view animated:YES];
    UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 58.0, 61.5)];
    NSMutableArray *images = [NSMutableArray array];
    for (int i = 1; i <= 7; i++) {
        UIImage *image = [UIImage imageNamed:[NSString stringWithFormat:@"ODProgressHUD_%d", i]];
        if (image) {
            [images addObject:image];
        }
    }
    imageView.animationImages = images;
    imageView.animationDuration = 1.0;
    [imageView startAnimating];
    hud.mode = MBProgressHUDModeCustomView;
    hud.customView = imageView;
    hud.removeFromSuperViewOnHide = YES;
    return hud;
}

@end
