//
//  YRTenementInfoCell.m
//  YRCommunity
//
//  Created by 良好心态 on 15/7/25.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "YRTenementInfoCell.h"
#import "UIView+CustomView.h"

static const CGFloat kImageWith = 60.0;
static const CGFloat kImageHeight = 40.0;
@implementation YRTenementInfoCell
{
    UIView * _transverseLine1;
    UIView * _transverseLine2;
    UIView * _longLine;
    UILabel *_tenementInfoLabel;
    UIImageView *_tenementInfoImage;
    UIButton *_tenementInfoBtn;
    UILabel *_tenementADLabel;
    UIImageView *_tenementADImage;
    UIButton *_tenementADBtn;

    
}

+ (CGFloat)defaultHeight
{
    return 100;
}

#pragma mark - creatView

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        [self createContent];
    }
    return self;
}

- (void)createContent
{
    _transverseLine1 = [[UIView alloc] initWithFrame:CGRectZero];
    _transverseLine1.backgroundColor = [UIColor lightGrayColor];
    [self.contentView addSubview:_transverseLine1];
    
    _transverseLine2 = [[UIView alloc] initWithFrame:CGRectZero];
    _transverseLine2.backgroundColor = [UIColor lightGrayColor];
    [self.contentView addSubview:_transverseLine2];
    
    _longLine = [[UIView alloc] initWithFrame:CGRectZero];
    _longLine.backgroundColor = [UIColor lightGrayColor];
    [self.contentView addSubview:_longLine];
    
    _tenementInfoImage = [[UIImageView alloc] initWithFrame:CGRectZero];
    _tenementInfoImage.contentMode = UIViewContentModeScaleAspectFill;
    _tenementInfoImage.image = [UIImage imageNamed:@"mywuyexinxi"];
    [self.contentView addSubview:_tenementInfoImage];
    
    _tenementInfoLabel = [[UILabel alloc] initWithFrame:CGRectZero];
    _tenementInfoLabel.text = NSLocalizedString(@"物业信息", nil);
    _tenementInfoLabel.textColor = [UIColor blackColor];
    _tenementInfoLabel.font = [UIFont systemFontOfSize:12];
    _tenementInfoLabel.textAlignment = NSTextAlignmentCenter;
    _tenementInfoLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_tenementInfoLabel];
    
    _tenementInfoBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    _tenementInfoBtn.backgroundColor = [UIColor clearColor];
    _tenementInfoBtn.frame = CGRectZero;
    [_tenementInfoBtn addTarget:self action:@selector(buttonInfoClick) forControlEvents:UIControlEventTouchUpInside];
    [self.contentView addSubview:_tenementInfoBtn];
    
    _tenementADImage = [[UIImageView alloc] initWithFrame:CGRectZero];
    _tenementADImage.contentMode = UIViewContentModeScaleAspectFill;
    _tenementADImage.image = [UIImage imageNamed:@"mywuyegonggao"];
    [self.contentView addSubview:_tenementADImage];
    
    _tenementADLabel = [[UILabel alloc] initWithFrame:CGRectZero];
    _tenementADLabel.text = NSLocalizedString(@"物业公告", nil);
    _tenementADLabel.textColor = [UIColor blackColor];
    _tenementADLabel.font = [UIFont systemFontOfSize:12];
    _tenementADLabel.textAlignment = NSTextAlignmentCenter;
    _tenementADLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_tenementADLabel];
    
    _tenementADBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    _tenementADBtn.backgroundColor = [UIColor clearColor];
    _tenementADBtn.frame = CGRectZero;
    [_tenementADBtn addTarget:self action:@selector(buttonADClick) forControlEvents:UIControlEventTouchUpInside];
    [self.contentView addSubview:_tenementInfoBtn];

}

- (void)layoutSubviews
{
    [super layoutSubviews];
    _transverseLine1.frame = CGRectMake(0, 0, self.contentView.width, 0.5);
    _transverseLine2.frame = CGRectMake(0, self.contentView.bottom - 0.5, self.width, 0.5);
    CGFloat space = 15;
    _longLine.frame = CGRectMake( 0, space, 0.5,self.height - space * 2 );
    _longLine.center = CGPointMake(self.center.x, self.height/2);
    
    CGFloat tTop = 25;
    _tenementInfoImage.frame = CGRectMake( 0, tTop, kImageWith,kImageHeight);
    _tenementInfoImage.center = CGPointMake(self.width/4, _tenementInfoImage.center.y);
    _tenementInfoLabel.frame = CGRectMake(_tenementInfoImage.left - space, _tenementInfoImage.bottom ,_tenementInfoImage.width +space *2 , 25);
    _tenementInfoBtn.frame = CGRectMake(_tenementInfoImage.left, _tenementInfoImage.top, _tenementInfoLabel.width, _tenementInfoLabel.bottom - _tenementInfoImage.top);
    _tenementADImage.frame = CGRectMake( 0, tTop, kImageWith,kImageHeight);
    _tenementADImage.center = CGPointMake(self.width/2 + self.width/4, _tenementADImage.center.y);
    _tenementADLabel.frame = CGRectMake(_tenementADImage.left - space, _tenementADImage.bottom ,_tenementInfoImage.width +space *2 , 25);
    _tenementADBtn.frame = CGRectMake(_tenementADImage.left, _tenementADImage.top, _tenementInfoLabel.width, _tenementADLabel.bottom - _tenementADImage.top);

}

- (void)awakeFromNib {
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

}

#pragma mark - action

- (void)buttonInfoClick
{

}

- (void)buttonADClick
{

}


@end
