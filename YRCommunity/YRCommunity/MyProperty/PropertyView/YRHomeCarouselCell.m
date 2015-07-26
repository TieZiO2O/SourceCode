//
//  ODHomeCarouselCell.m
//  ADMall
//
//  Created by xdmini on 15/6/1.
//  Copyright (c) 2015年 AoDing. All rights reserved.
//

#import "YRHomeCarouselCell.h"
#import "UIView+CustomView.h"
#import "UIColor+Utilities.h"
#import "ODFixScreen.h"
//#import "ADBizView.h"
//#import "ODBannerList.h"
//#import "ODBannerItem.h"
//#import "UIImageView+SDCache.h"

#define kColorLineGray ColorForRGB(181,181 ,181,1)
#define kTimerDrugan 5

static const CGFloat kPageControlHeight = 6.f;

@implementation YRHomeCarouselCell
{
    iCarousel *_posterView;
    UIPageControl *_pageControl;
    UIView *_lineView;
    NSTimer *_timer;
    NSInteger _index;
}

+ (CGFloat)defaultHeight
{
    CGFloat aspectRatio = 640.0 / 240.0;
    return ceil(kScreenWidth / aspectRatio);
}


- (void)awakeFromNib {
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];
}

- (void)layoutSubviews
{
    [super layoutSubviews];

    _lineView.frame = CGRectMake(0, self.height - 0.5, self.width, 0.5);
    _pageControl.frame = CGRectMake(0, self.height - kPageControlHeight - 8.0, self.width, kPageControlHeight);
}

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [ super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
            
        _posterView = [[iCarousel alloc] initWithFrame:self.bounds];
        _posterView.autoresizingMask = UIViewAutoresizingFlexibleHeight|UIViewAutoresizingFlexibleWidth;
        _posterView.delegate = self;
        _posterView.dataSource = self;
        _posterView.type = iCarouselTypeLinear;
        _posterView.pagingEnabled = YES;
        [self.contentView addSubview:_posterView];
        
        _pageControl = [[UIPageControl alloc] initWithFrame:CGRectMake(0, self.height - kPageControlHeight - 8.0, self.width, kPageControlHeight)];
        _pageControl.currentPageIndicatorTintColor = ColorForHexAlpha(0xffffff, 0.8);
        _pageControl.hidesForSinglePage = YES;
        [_pageControl addTarget:self action:@selector(pageControlValueChanged) forControlEvents:UIControlEventValueChanged];
        [self.contentView addSubview:_pageControl];
        
        _lineView = [[UIView alloc] initWithFrame:CGRectMake(0, self.contentView.height - 0.5, self.width, 0.5)];
        _lineView.backgroundColor = kColorLineGray;
        [self.contentView addSubview:_lineView];
        
        _timer = [NSTimer scheduledTimerWithTimeInterval:kTimerDrugan target:self selector:@selector(timeChange:) userInfo:nil repeats:YES];
    }
    return self;
}

- (void)dealloc
{
    [_timer invalidate];
}

#pragma mark - otherMethod

- (void)timeChange:(NSTimer *)time
{
    _index ++;
//    if (_index == self.bannerList.count +1) {
//        _index = 1;
//    }
    [_posterView scrollToItemAtIndex:_index animated:YES];
}


- (NSInteger)currentPage
{
    return _posterView.currentItemIndex;
}

//- (void)refreshDataWithBarnnerList:(ODBannerList *)bannerList
//{
//    self.bannerList = bannerList;
//    [_posterView reloadData];
//  //  _pageControl.numberOfPages = self.bannerList.count;
//}

- (void)pageControlValueChanged
{
    [_posterView scrollToItemAtIndex:_pageControl.currentPage animated:YES];
}

#pragma mark - iCarouselDataSource, iCarouselDelegate

- (NSInteger)numberOfItemsInCarousel:(iCarousel *)carousel
{
//    carousel.scrollEnabled = self.bannerList.count > 1;
//    return self.bannerList.count;
    return 1;
}

- (UIView *)carousel:(iCarousel *)carousel viewForItemAtIndex:(NSInteger)index reusingView:(UIView *)view
{
    UIImageView *pageImgView = (UIImageView *)view;
    if (pageImgView == nil) {
        pageImgView = [[UIImageView alloc] initWithFrame:carousel.bounds];
        pageImgView.clipsToBounds = YES;
        pageImgView.contentMode = UIViewContentModeScaleToFill;
    }
    UIImage *image = [UIImage imageNamed:@"publicity 2.jpg"];
    pageImgView.image = image;
    
//    if (index < self.bannerList.count) {
//        ODBannerItem *item = self.bannerList[index];
//        NSURL *url = [NSURL URLWithString:item.bannerImageURL];
//        [pageImgView setImageWithURL:url];
//    }
    return pageImgView;
}

- (CGFloat)carousel:(iCarousel *)_carousel valueForOption:(iCarouselOption)option withDefault:(CGFloat)value
{
    return (option == iCarouselOptionWrap) ? YES : value;
}

- (void)carouselCurrentItemIndexDidChange:(iCarousel *)carousel
{
    _pageControl.currentPage = carousel.currentItemIndex;
}

//- (void)carousel:(iCarousel *)carousel didSelectItemAtIndex:(NSInteger)index
//{
//    if (self.selectItemHandler) {
//        self.selectItemHandler(self,index);
//    }
//}

@end
