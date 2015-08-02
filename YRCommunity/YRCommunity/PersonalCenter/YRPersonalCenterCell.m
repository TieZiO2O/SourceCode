//
//  YRPersonalCenterCell.m
//  YRCommunity
//
//  Created by 良好心态 on 15/8/1.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "YRPersonalCenterCell.h"
#import "UIView+CustomView.h"
#define kCellHeight 40

@implementation YRPersonalCenterCell{

    UIImageView *_iconImage;
    UIImageView *_jumpImage;
    UILabel *_titleLabel;

}

+ (CGFloat)defaultHeight
{
    return 40;
}

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        [self createContent];
    }
    return self;
}

- (void)awakeFromNib {
    
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

}

- (void)updataTitle:(NSString *)title andImage:(NSString *)imagename
{
    _iconImage.image = [UIImage imageNamed:imagename];
    _titleLabel.text = title;
}

- (void)createContent
{
    _iconImage = [[UIImageView alloc] init];
    [self.contentView addSubview:_iconImage];
    
    _titleLabel  =[[UILabel alloc] init];
    _titleLabel.font = [UIFont systemFontOfSize:12];
    _titleLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_titleLabel];
    
    _jumpImage = [[UIImageView alloc] init];
    _jumpImage.image = [UIImage imageNamed:@"personal_submenu_right"];
    [self.contentView addSubview:_jumpImage];
    
    _lineView = [[UIView alloc] init];
    _lineView.hidden = YES;
    _lineView.backgroundColor = [UIColor  lightGrayColor];
    [self.contentView addSubview:_lineView];
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    _iconImage.frame = CGRectMake( 5, 2, 36, 36);
    _titleLabel.frame = CGRectMake(46, 5, self.width - 50, 30);
    _jumpImage.frame = CGRectMake( self.width - 20, 0, 12, 12);
    _jumpImage.center = CGPointMake(_jumpImage.center.x, self.height/2);
    _lineView.frame = CGRectMake( 0,self.height - 0.5 ,self.width, 0.5);
    
}
@end
