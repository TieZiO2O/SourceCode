//
//  TitleView.h
//  YRCommunity
//
//  Created by windear on 15/7/22.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TitleView : UIView

@property (weak, nonatomic) IBOutlet UILabel *titleLabel;

@property (weak, nonatomic) IBOutlet UIImageView *titleImage;

+(TitleView *)instanceNormalTitleView;//只有titleLabel

+(TitleView *)instanceImgTitleView;//既有titleLabel 又有titleImage

@end
