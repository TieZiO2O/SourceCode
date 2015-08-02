//
//  YRPersonalCenterCell.h
//  YRCommunity
//
//  Created by 良好心态 on 15/8/1.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface YRPersonalCenterCell : UITableViewCell

+ (CGFloat)defaultHeight;

- (void)updataTitle:(NSString *)title andImage:(NSString *)imagename;

@property (nonatomic, strong) UIView *lineView;
@end
