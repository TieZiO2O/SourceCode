//
//  YRPersonCenterHeaderCell.h
//  YRCommunity
//
//  Created by 良好心态 on 15/7/31.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^ButtonClickBlock)();

@interface YRPersonCenterHeaderCell : UITableViewCell

+ (CGFloat)defaultHeight;

@property (nonatomic, assign) BOOL isLogin;
@property (nonatomic, copy) ButtonClickBlock buttonClickBlock;
@end
