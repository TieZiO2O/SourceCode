//
//  TouchUnitView.h
//  YRCommunity
//
//  Created by 王金龙 on 15/7/19.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TouchUnitView : UIView
@property (weak, nonatomic) IBOutlet UIImageView *touchUnitImage;
@property (weak, nonatomic) IBOutlet UILabel *touchUnitLabel;
@property (weak, nonatomic) IBOutlet UIButton *touchUnitBtn;

+(TouchUnitView *)instanceTouchUnitView;

@end
