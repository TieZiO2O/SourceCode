//
//  YRPersonCenterHeaderCell.m
//  YRCommunity
//
//  Created by 良好心态 on 15/7/31.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "YRPersonCenterHeaderCell.h"
#import "UIView+CustomView.h"
#import "ODFixScreen.h"

#define kColorLineGray ColorForRGB(181,181 ,181,1)
#define kTimerDrugan 5

@implementation YRPersonCenterHeaderCell{
    UILabel *_warnLabel;
    UIButton *_loginButton;
    UIImageView *_backImageView;
    UIImageView *_headerImageView;
    UILabel *_nameLabel;
}

+ (CGFloat)defaultHeight
{
    CGFloat aspectRatio = 640.0 / 240.0;
    return ceil(kScreenWidth / aspectRatio);
}


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self createContentView];
    }
    return self;
}

#pragma mark - createView

- (void)createContentView
{
    _backImageView = [[UIImageView alloc] init];
    _backImageView.image = [UIImage imageNamed:@"publicity2.jpg"];
    [self.contentView addSubview:_backImageView];
    
    _warnLabel = [[UILabel alloc] init];
    _warnLabel.text = NSLocalizedString(@"您还没有登录", nil);
    _warnLabel.font = [UIFont systemFontOfSize:12];
    _warnLabel.textAlignment = NSTextAlignmentCenter;
    _warnLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_warnLabel];
    
    _loginButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [_loginButton addTarget:self action:@selector(loginButtonClick) forControlEvents:UIControlEventTouchUpInside];
    [_loginButton setTitle:NSLocalizedString(@"登录", nil)
                  forState:UIControlStateNormal];
    [_loginButton setTitleColor:[UIColor blackColor]
                       forState:UIControlStateNormal];
    _loginButton.layer.cornerRadius = 8;
    _loginButton.layer.borderWidth = 1;
    _loginButton.layer.borderColor = [UIColor greenColor].CGColor;
    [self.contentView addSubview:_loginButton];
    
    _headerImageView = [[UIImageView alloc] init];
    _headerImageView.image = [UIImage imageNamed:@"personal_already_login_image"];
    [self.contentView addSubview:_headerImageView];
    
    _nameLabel = [[UILabel alloc] init];
    _nameLabel.font = [UIFont systemFontOfSize:12];
    _nameLabel.textAlignment = NSTextAlignmentCenter;
    _nameLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_nameLabel];
    
    if (_isLogin) {
        _nameLabel.hidden = NO;
        _headerImageView.hidden = NO;
        _loginButton.hidden = YES;
        _warnLabel.hidden = YES;
    }
    else
    {
        _nameLabel.hidden = YES;
        _headerImageView.hidden = YES;
        _loginButton.hidden = NO;
        _warnLabel.hidden = NO;
    }

}

- (void)loginButtonClick
{
    if (self.buttonClickBlock) {
        self.buttonClickBlock();
    }
}

- (void)awakeFromNib {
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

}

- (void)layoutSubviews
{
    [super layoutSubviews];
    _backImageView.frame = self.bounds;
    _warnLabel.frame = CGRectMake( 0, 20,self.width, 20);
    _loginButton.frame = CGRectMake( 0, 0, 80, 30);
    _loginButton.center = CGPointMake(self.width/2, _warnLabel.bottom + _loginButton.height/2 + 10);
    _headerImageView.frame = CGRectMake( 0, 0, 40, 40);
    _headerImageView.center = CGPointMake(self.width/2, _warnLabel.bottom + _warnLabel.height/2);
    _nameLabel.frame = CGRectMake(0, _headerImageView.bottom, self.width, 20);
    
}

@end
