using CleanThatCode.Community.Repositories.Data;
using System;
using System.Collections.Generic;
using System.Text;
using CleanThatCode.Community.Models.Entities;

namespace CleanThatCode.Community.Tests.Mocks
{
    class CleanThatCodeDbContextMock : ICleanThatCodeDbContext
    {
        public IEnumerable<Comment> Comments
        {
            get
            {
                return FakeData.Comments;
            }
        }

        public IEnumerable<Post> Posts
        {
            get
            {
                return FakeData.Posts;
            }
        }
    }
}
