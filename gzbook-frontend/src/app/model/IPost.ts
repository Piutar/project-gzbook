import {IComment} from './IComment';

export interface IPost {
  id: number;
  userId: number;
  posterAvatar: string;
  posterName: string;
  textPost: string;
  imageUrl: string;
  videoPost: string;
  linkPost: string;
  createdTime: string;
  postLike: number;
  postDislike: number;
  status: number;
  commentList: IComment[];
}
