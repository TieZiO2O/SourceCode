//
//  LifeServicesTableViewCell0.m
//  YRCommunity
//
//  Created by 王金龙 on 15/7/19.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "LifeServicesTableViewCell0.h"

@implementation LifeServicesTableViewCell0

- (void)awakeFromNib {
    // Initialization code
}

-(void)addBgScrollViewWithFrame:(CGRect)frame andContentSize:(CGSize)cSize;
{
    _bgScrollView = [[UIScrollView alloc]initWithFrame:frame];
    _bgScrollView.contentSize = cSize;
    [self.contentView addSubview:_bgScrollView];
    
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
