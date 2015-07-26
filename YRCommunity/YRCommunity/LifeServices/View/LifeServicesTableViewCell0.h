//
//  LifeServicesTableViewCell0.h
//  YRCommunity
//
//  Created by 王金龙 on 15/7/19.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LifeServicesTableViewCell0 : UITableViewCell
//@property (weak, nonatomic) IBOutlet UIScrollView *bgScrollView;
@property (strong, nonatomic) UIScrollView *bgScrollView;

-(void)addBgScrollViewWithFrame:(CGRect)frame andContentSize:(CGSize)cSize;

@end
