export interface IComment {
  id: number;
  userId: number;
  postId: number;
  content: string;
  commentLike: number;
  commentDislike: number;
  commentTime: string;
  commenterName: string;
  commenterAvatar: string;
  edited: number;
}
