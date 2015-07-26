//
//  ODHomeCarouselCell.h
//  ADMall
//
//  Created by xdmini on 15/6/1.
//  Copyright (c) 2015年 AoDing. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "iCarousel.h"

//@class ODBannerList;

@interface YRHomeCarouselCell : UITableViewCell<iCarouselDataSource, iCarouselDelegate>

+ (CGFloat)defaultHeight;

//- (void)refreshDataWithBarnnerList:(ODBannerList *)bannerList;
- (NSInteger)currentPage;

//@property (nonatomic, strong) ODBannerList *bannerList;
//@property (nonatomic, copy) void (^selectItemHandler)(ODHomeCarouselCell *senderView,NSInteger index);

@end
