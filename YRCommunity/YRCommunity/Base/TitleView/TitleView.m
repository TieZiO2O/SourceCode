//
//  TitleView.m
//  YRCommunity
//
//  Created by windear on 15/7/22.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "TitleView.h"

@implementation TitleView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

+(TitleView *)instanceNormalTitleView
{
    return [[[NSBundle mainBundle] loadNibNamed:@"TitleView0" owner:nil options:nil] lastObject];
}

+(TitleView *)instanceImgTitleView
{
    return [[[NSBundle mainBundle] loadNibNamed:@"TitleView1" owner:nil options:nil] lastObject];
}

@end
