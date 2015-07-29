//
//  YRLifeInfoCell.m
//  YRCommunity
//
//  Created by 良好心态 on 15/7/29.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "YRLifeInfoCell.h"
#import "UIView+CustomView.h"

#define kLeftSpace 10
#define kTopSpace 20
#define kImageHeight 60
#define kCellHeight 80

#define kTitleFont [UIFont systemFontOfSize:14]
#define kContentFont [UIFont systemFontOfSize:12]

@implementation YRLifeInfoCell{

    UIImageView *_iconImage;
    UILabel *_titleLabel;
    UILabel *_contentLabel;
    UIView *_lineView;

}

+ (CGFloat)defaultHeight
{
    return kCellHeight;
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
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

#pragma mark - createView

- (void)createContent
{
    _iconImage = [[UIImageView alloc] init];
    _iconImage.image = [UIImage imageNamed:@"kaixin.jpg"];
    [self.contentView addSubview:_iconImage];
    
    _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
    _titleLabel.text = NSLocalizedString(@"开心一刻", nil);
    _titleLabel.backgroundColor = [UIColor clearColor];
    _titleLabel.font = kTitleFont;
    [self.contentView addSubview:_titleLabel];
    
    _contentLabel = [[UILabel alloc] initWithFrame:CGRectZero];
    _contentLabel.font = kContentFont;
    _contentLabel.textColor = [UIColor lightGrayColor];
    _contentLabel.text = NSLocalizedString(@"笑一笑，十年少", nil);
    _contentLabel.backgroundColor = [UIColor clearColor];
    [self.contentView addSubview:_contentLabel];
    
    _lineView = [[UIView alloc] init];
    _lineView.backgroundColor = [UIColor lightGrayColor];
    [self.contentView addSubview:_lineView ];
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    _iconImage.frame = CGRectMake(kLeftSpace, 10, kImageHeight, kImageHeight);
    _titleLabel.frame = CGRectMake(_iconImage.right + 10, kTopSpace, self.width - _iconImage.right, 20);
    _contentLabel.frame = CGRectMake( _titleLabel.left, _titleLabel.bottom, _titleLabel.width,20 );
    _lineView.frame = CGRectMake( 0, self.height - 0.5, self.width, 0.5);
    
}


@end
