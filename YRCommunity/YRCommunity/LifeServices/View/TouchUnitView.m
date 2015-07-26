//
//  TouchUnitView.m
//  YRCommunity
//
//  Created by 王金龙 on 15/7/19.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "TouchUnitView.h"

@implementation TouchUnitView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

+(TouchUnitView *)instanceTouchUnitView
{
    return [[[NSBundle mainBundle] loadNibNamed:@"TouchUnitView" owner:nil options:nil] lastObject];
}

@end
